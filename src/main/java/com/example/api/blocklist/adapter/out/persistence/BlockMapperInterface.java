package com.example.api.blocklist.adapter.out.persistence;


import com.example.api.blocklist.domain.BlockList;
import com.example.api.blocklist.dto.AddBlockDto;
import com.example.api.user.domain.BlockUser;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BlockMapperInterface {
    @Mapping(source = "blocklistUserId", target = "blocklistUserId.userId")
    BlockList toDomain(AddBlockDto addBlockDto);
    @Mapping(source = "blocklistUserId.userId", target = "blocklistUserId.userId")
    List<BlockList> toDomainList(List<BlockListEntity> blockListEntities);
    @Mapping(source = "blocklistUserId.userId", target = "blocklistUserId.userId")
    BlockListEntity toEntity(BlockList blockList);
    @Mapping(source = "blocklistUserId.userId", target = "userId")
    @Mapping(source = "blocklistUserId.username", target = "username")
    List<BlockUser> toDomainBlockUserList(List<BlockList> blockLists);
//    @Mapping(source = "blocklistUserId.userId", target = "userId")
//    List<BlockUser> toDomainList(List<BlockListEntity> blockListEntities);
}
