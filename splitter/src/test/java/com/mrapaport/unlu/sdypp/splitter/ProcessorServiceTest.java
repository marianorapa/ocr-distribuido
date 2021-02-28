package com.mrapaport.unlu.sdypp.splitter;

import com.mrapaport.unlu.sdypp.splitter.messaging.MessageBroker;
import com.mrapaport.unlu.sdypp.splitter.service.ProcessorService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessorServiceTest {

    @Autowired
    ProcessorService processorService;

//    @MockBean
    @Autowired
    MessageBroker messageBroker;

    @Test
    public void whenProcessImagesIsGivenAnEmptyList_ShouldThrowException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            processorService.processImages(new ArrayList<>());
        });
    }

    @Test
    public void whenProcessImagesIsGivenAListWithFiles_ShouldReturnUUID(){
        List<MultipartFile> files = new ArrayList<>();
        files.add(new MockMultipartFile("name", new byte[]{}));
        UUID uuid = processorService.processImages(files);
        Assertions.assertNotNull(uuid);

        System.out.println(Mockito.mockingDetails(messageBroker).printInvocations());

    }

}
