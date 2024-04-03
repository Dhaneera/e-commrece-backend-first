package edu.icet.clothify.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.dto.CollectionDto;
import edu.icet.clothify.entity.Collection;
import edu.icet.clothify.repository.CollectionRepository;
import edu.icet.clothify.service.impl.CollectionServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Collection Service Testing")
public class CollectionServiceTest {
    @Mock
    CollectionRepository collectionRepository;

    @Mock
    ObjectMapper objectMapper;

    @InjectMocks
    CollectionServiceImpl collectionServiceImpl;

    @Mock
     CollectionService collectionService;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Nested
    @Order(1)
    @DisplayName("Service save")
    class CollectionServiceSave {
        @Test
        @Order(1)
        @DisplayName("Save Collection Service")
        public void CollectionService_SaveCollection_ReturnObject(){
            //Given
            Collection collection = Collection.builder().id(1L).name("Winter").build();
            CollectionDto collectionDto = CollectionDto.builder().id(1L).name("Winter").build();
            //When
            when(collectionRepository.save(any())).thenReturn(collection);
            when(objectMapper.convertValue(any(), (JavaType) any())).thenReturn(collection);
            boolean isSaved=collectionServiceImpl.saveCollection(collectionDto);
            //Then
            Assertions.assertTrue(isSaved);
        }
    }
    @Nested
    @Order(2)
    @DisplayName("Service View")
    class CategoryServiceView{
        @Test
        @Order(1)
        @DisplayName("Service Collection GetAll")
        public void CollectionService_ViewCollection_ReturnObject(){

            //Given

        }
        @Test
        @Order(2)
        @DisplayName("Service Collection GetByName")
        public void CollectionService_ViewCollectionByName_ReturnObject(){

            //Given
            Long id =1L;
            String collectionName ="Winter";

            Collection collection = Collection.builder().id(id).name(collectionName).build();
            CollectionDto collectionDto = CollectionDto.builder().id(id).name(collectionName).build();


            //When
            when(collectionRepository.getByName(collectionName)).thenReturn(collection);
            when(collectionService.getCollectionByName(collectionName)).thenReturn(collectionDto);

            // Then
            verify(collectionRepository).getByName(collectionName);

        }
        @Test
        @Order(3)
        @DisplayName("Service Collection GeById")
        public void CollectionService_ViewCollectionById_ReturnObject(){
            //Given
            Collection collection = Collection.builder().id(1L).name("Winter").build();
            CollectionService collectionService = Mockito.mock(CollectionService.class);

            //When
            when(collectionService.getCollectionById(collection.getId())).thenReturn(CollectionDto.builder().build());
            CollectionDto foundCollection =collectionService.getCollectionById(collection.getId());

            //Then
            assertNotNull(foundCollection,"CollectionDto should not be null");
        }


    }
    @Nested
    @Order(3)
    @DisplayName("Service Delete")
    class  CollectionServiceDelete{
        @Test
        @Order(1)
        @DisplayName("Service Collection DeleteById")
        public void CollectionService_DeleteById_ReturnVoid(){
            //Given
            Long id = 1L;
            String collectionName = "Summer";
            Collection collection = Collection.builder().id(id).name(collectionName).build();
            CollectionDto collectionDto = CollectionDto.builder().id(id).name(collectionName).build();

            //When
            when(collectionService.getCollectionById(collection.getId())).thenReturn(collectionDto);
            doNothing().when(collectionRepository).deleteById(collection.getId());

            boolean isDeleted = collectionService.deleteCollectionById(collection.getId());

            //Then
            verify(collectionService).deleteCollectionById(collection.getId());

        }
    }


}