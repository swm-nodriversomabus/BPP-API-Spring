package com.example.api.blocklist.service;

import com.example.api.blocklist.adapter.out.persistence.BlockMapperInterface;
import com.example.api.blocklist.application.port.in.AddBlockUsecase;
import com.example.api.blocklist.application.port.in.GetListUsecase;
import com.example.api.blocklist.application.port.in.ReleaseBlockUsecase;
import com.example.api.blocklist.application.port.out.AddBlockUserPort;
import com.example.api.blocklist.application.port.out.GetBlockListPort;
import com.example.api.blocklist.application.port.out.ReleaseBlockPort;
import com.example.api.blocklist.domain.BlockList;
import com.example.api.blocklist.dto.AddBlockDto;
import com.example.api.blocklist.dto.DeleteBlockDto;
import com.example.api.user.domain.BlockUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BlockListService implements AddBlockUsecase, GetListUsecase, ReleaseBlockUsecase {
    private final AddBlockUserPort addBlockUserPort;
    private final GetBlockListPort getBlockListPort;
    private final ReleaseBlockPort releaseBlockPort;
    private final BlockMapperInterface blockMapperInterface;

    @Override
    @Transactional
    public void addBlockUser(AddBlockDto addBlockDto, UUID userId) {
        addBlockDto.setUserId(userId);
        BlockList blockList = blockMapperInterface.toDomain(addBlockDto);
        addBlockUserPort.addBlockUser(blockList);
    }

    @Override
    public List<BlockUser> getBlockList(Pageable pageable, UUID userId) {
        List<BlockList> blockList = getBlockListPort.getBlockUserList(userId, pageable);
        return blockList.stream().map(BlockList::getBlocklistUserId).toList();
    }

    @Override
    @Transactional
    public void releaseBlockUser(DeleteBlockDto addBlockDto, UUID userId) {
        releaseBlockPort.deleteBlockUser(userId, addBlockDto.getBlocklistUserId());
    }
}