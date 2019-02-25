package com.example.backservice.service;

import com.example.backservice.domain.EntityCodEnum;
import com.example.backservice.dto.FileDTO;
import com.example.backservice.dto.ReportDTO;
import com.example.backservice.model.Customer;
import com.example.backservice.model.Sale;
import com.example.backservice.model.Saleman;
import com.example.backservice.properties.InitialProperties;
import com.example.backservice.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.example.backservice.properties.InitialProperties.*;

@Service
public class FileService {

    @Autowired
    private FileRepository repository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SalemanService salemanService;

    @Autowired
    private SaleService saleService;

    @Async
    public void prepareInitialProcessingToExistingsFiles() {
        try {
            waitForReactivityOfFolders();
            File dir = new File(INPUT_DIR);
            Path temp = prepareTempDir();
            repository.refreshFiles(dir, temp);
            deleteTempPathIfExists();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Path prepareTempDir() throws IOException {
        deleteTempPathIfExists();
        return repository.createDirectory(TEMP_DIR);
    }

    private void deleteTempPathIfExists() throws IOException {
        repository.deleteFileIfExists(Paths.get(TEMP_DIR).toFile());
    }

    private void waitForReactivityOfFolders() throws InterruptedException {
        Thread.sleep(WAIT_REACTIVITY_FOLDER);
    }

    public FileDTO readFile(String file) throws IOException {
        FileDTO fileDTO = new FileDTO();
        String fileName = InitialProperties.INPUT_DIR+file;
        String content = repository.readFile(fileName);

        for (int i = 0; i < content.length();){
            Integer startIndex = null;
            Integer endIndex = null;
            while (!isIdEntity(content, i)){
                i++;
            }
            startIndex=i;
            i=i+3;
            while (!isIdEntity(content, i) && latestPosition(i, content) > i){
                i++;
            }
            if (latestPosition(i, content) <= i){
                endIndex=content.length()-1;
                i=content.length();
            }else{
                endIndex=i;
            }
            String entity = content.substring(startIndex,endIndex);
            System.out.println("entity:"+entity +", i:" + i);
            buildEntity(new StringBuilder(entity), fileDTO);
        }
        saveFileDTO(fileDTO);
        updateReport();
        return fileDTO;
    }

    private void updateReport() throws IOException {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setAmountCustomer(customerService.count());
        reportDTO.setAmountSaleman(salemanService.count());
        reportDTO.setIDSaleRecord(saleService.findMostExpensive());
        printReport(reportDTO);
    }

    private void printReport(ReportDTO reportDTO) throws IOException {
        Path path = repository.createFile(NAME_FILE_OUTPUT);
        StringBuilder content = new StringBuilder();
        content.append(AMOUNT_OF_CLIENTS).append(reportDTO.getAmountCustomer());
        content.append("\n");
        content.append(AMOUNT_OF_SALEMAN).append(reportDTO.getAmountSaleman());
        content.append("\n");
        content.append(MOST_EXPENSIVE_SALE).append(reportDTO.getIDSaleRecord().getIdSale());
        content.append("  ").append(reportDTO.getIDSaleRecord().getPrice());
        content.append("\n");
        content.append(WORST_SALEMAN).append(reportDTO.getBadSeller());
        Files.write(path, content.toString().getBytes());
    }

    private void saveFileDTO(FileDTO fileDTO) {
        salemanService.saveAll(fileDTO);
        customerService.saveAll(fileDTO);
        saleService.saveAll(fileDTO);
    }

    private boolean isIdEntity(String content, int i) {
        Integer startIndex = i;
        Integer endIndex = latestPosition(i, content);
        if (startIndex>=endIndex){
            return false;
        }
        String interval = content.substring(startIndex, endIndex);
        if (interval.equals(EntityCodEnum.CUSTOMER.getId())){
            return true;
        }else if (interval.equals(EntityCodEnum.SALEMAN.getId())){
            return true;
        }else if (interval.equals(EntityCodEnum.SALE.getId())){
            return true;
        }
        return false;
    }

    private Integer latestPosition(int i, String content) {
        return (i + 3 >= content.length() ? content.length() - 1 : i + 3);
    }

    private void buildEntity(StringBuilder entity, FileDTO fileDTO) throws IOException {
        entity.trimToSize();
        String idEntity = entity.substring(0,3);
        switch (EntityCodEnum.valueEnum(idEntity)) {
            case SALEMAN:
                Saleman saleman = salemanService.buildSalemanByString(entity);
                fileDTO.getSalesman().add(saleman);
                break;
            case CUSTOMER :
                Customer customer = customerService.buildCustomerByString(entity);
                fileDTO.getCustomers().add(customer);
                break;
            case SALE:
                Sale sale = saleService.buildSaleByString(entity);
                fileDTO.getSales().add(sale);
                break;
            default:
                throw new IOException("Arquivo com dados invalidos!");
        }
    }
}
