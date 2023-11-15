package com.example.api.blocklist.adapter.out.persistence;

import com.example.api.blocklist.application.port.out.AddBlockUserPort;
import com.example.api.blocklist.application.port.out.GetBlockListPort;
import com.example.api.blocklist.application.port.out.ReleaseBlockPort;
import com.example.api.blocklist.domain.BlockList;
import com.example.api.blocklist.repositorty.BlockListRepository;
import com.example.api.chat.adapter.out.persistence.ChatEntity;
import com.example.api.user.domain.BlockUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Block;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



@Repository
@RequiredArgsConstructor
@Slf4j
public class BlockUserPersistentAdapter implements AddBlockUserPort, GetBlockListPort, ReleaseBlockPort {
    private final BlockListRepository blockListRepository;
    private final BlockMapperInterface blockMapperInterface;

    @Override
    public void addBlockUser(BlockList blockList) {
        blockListRepository.save(blockMapperInterface.toEntity(blockList));
    }

    @Override
    public List<BlockList> getBlockUserList(UUID userId, Pageable pageable) {
        Page<BlockListEntity> ret = blockListRepository.findAllByUserId(userId, pageable);
        if (ret != null && ret.hasContent()){
            return blockMapperInterface.toDomainList(ret.getContent());
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteBlockUser(UUID userId, UUID blockUserId) {
        blockListRepository.deleteByUserIdAndBlocklistUserId_UserId(userId, blockUserId);
    }
}
