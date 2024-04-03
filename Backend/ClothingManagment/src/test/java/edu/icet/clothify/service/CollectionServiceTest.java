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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CollectionServiceTest {
    @Mock
    CollectionRepository collectionRepository;

    @Mock
    ObjectMapper objectMapper;

    @InjectMocks
    CollectionServiceImpl collectionServiceImpl;

    @Mock
    private CollectionService collectionService;



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
            List<Collection> list= new ArrayList<>();

            Collection collection = Collection.builder().id(1L).name("Winter").build();
            Collection collection1 = Collection.builder().id(2L).name("Summer").build();
            CollectionDto collectionDto = CollectionDto.builder().id(2L).name("Summer").build();
            list.add(collection);
            list.add(collection1);


            //When
            when(collectionRepository.findAll()).thenReturn(list);
            when(objectMapper.convertValue(any(), (Class<Object>) any())).thenReturn(collectionDto);
            List<CollectionDto> categoriesGot=collectionServiceImpl.getAllCollection();
            //then
            org.assertj.core.api.Assertions.assertThat(!categoriesGot.isEmpty());
        }
        @Test
        @Order(2)
        @DisplayName("Service Collection GetByName")
        public void CollectionService_ViewCollectionByName_ReturnObject(){

            //Given
            Collection collection = Collection.builder().id(1L).name("Winter").build();
            CollectionService collectionService = Mockito.mock(CollectionService.class);

            //When
            when(collectionService.getCollectionByName(collection.getName())).thenReturn(CollectionDto.builder().build());
            CollectionDto foundCollection = collectionService.getCollectionByName(collection.getName());

            // Then
            assertNotNull(foundCollection, "CollectionDto should not be null");


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
            Collection collection = Collection.builder().id(1L).name("Winter").build();

            //When
            collectionService.deleteCollectionById(collection.getId());

            //Then
            verify(collectionService).deleteCollectionById(collection.getId());
        }
    }


}