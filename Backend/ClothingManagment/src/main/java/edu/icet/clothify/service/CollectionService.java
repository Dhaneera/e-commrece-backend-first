package edu.icet.clothify.service;


import edu.icet.clothify.dto.CollectionDto;

import java.util.List;

public interface CollectionService {
    List<CollectionDto> getAllCollection();

    CollectionDto getCollectionById(Long id);

    boolean deleteCollectionById(Long id);

    CollectionDto getCollectionByName(String name);

    boolean saveCollection(CollectionDto collectionDto);


}
