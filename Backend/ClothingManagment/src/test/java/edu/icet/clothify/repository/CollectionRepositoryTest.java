package edu.icet.clothify.repository;

import edu.icet.clothify.config.CollectionServiceException;
import edu.icet.clothify.entity.Collection;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CollectionRepositoryTest {
    @Autowired
    CollectionRepository collectionRepository;

    @Nested
    @Order(1)
    @DisplayName("Save Repository")
    class SaveCollection{
        @Test
        @Order(1)
        @DisplayName("Save Collection Repository")
        public void  CollectionRepository_SaveCollection_ReturnObject(){
            //Given
           Collection collection = Collection.builder().id(null).name("Winter").build();
            //When
            Collection saved = collectionRepository.save(collection);
            //Then
            assertEquals(collection.getId(), saved.getId());

        }
    }
    @Nested
    @Order(2)
    @DisplayName("Delete Repository")
    class DeleteCollection{

        @Test
        @Order(1)
        @DisplayName("Delete Collection Repository")
        public void  CollectionRepository_DeleteCollection_ReturnVoid() throws CollectionServiceException {
            //Given
            Collection collection = Collection.builder().id(null).name("Winter").build();
            //When
            Collection saved = collectionRepository.save(collection);
            collectionRepository.deleteById(collection.getId());
           Optional<Collection> byId = collectionRepository.findById(saved.getId());

            //Then
            assertNull(byId.orElse(null));
        }

    }
    @Nested
    @Order(3)
    @DisplayName("View Collection")
    class GetCollection{
        @Test
        @Order(1)
        @DisplayName("Get Collection  Repository")
        public void CollectionRepository_GetAllCollection_ReturnList(){
            //Given
            Collection collection = Collection.builder().id(null).name("Winter").build();

            //When
            Collection saved = collectionRepository.save(collection);
            Optional<Collection> byId = collectionRepository.findById(saved.getId());
            Collection collection1 = byId.get();

            //Then
            Assertions.assertEquals(collection1.getName(),"Winter");
        }
        @Test
        @Order(2)
        @DisplayName("Get Collection By Id Repository")
        public void CollectionRepository_GetCollectionById_ReturnList(){
            //Given
            Collection collection = Collection.builder().id(null).name("winter").build();

            //When
            Collection saved = collectionRepository.save(collection);
            Optional<Collection> byId = collectionRepository.findById(saved.getId());
            Collection collection1 = byId.get();

            //Then
            Assertions.assertEquals(collection1.getId(),saved.getId());


        }

        @Test
        @Order(3)
        @DisplayName("Get Collection By Name Repository")
        public void CollectionRepository_GetByName_ReturnList(){
            //Given
            Collection collection = Collection.builder().id(null).name("winter").build();

            //When

            Collection saved = collectionRepository.save(collection);
            Collection byName = collectionRepository.getByName(saved.getName());
            String name = byName.getName();

            //Then
            Assertions.assertEquals(name,saved.getName());
        }
    }


}
