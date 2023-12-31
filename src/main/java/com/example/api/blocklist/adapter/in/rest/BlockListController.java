package com.example.api.blocklist.adapter.in.rest;

import com.example.api.auth.domain.SecurityUser;
import com.example.api.blocklist.application.port.in.AddBlockUsecase;
import com.example.api.blocklist.application.port.in.ReleaseBlockUsecase;
import com.example.api.blocklist.application.port.in.GetListUsecase;
import com.example.api.blocklist.dto.AddBlockDto;
import com.example.api.blocklist.dto.DeleteBlockDto;
import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ErrorCodeEnum;
import com.example.api.common.utils.AuthenticationUtils;
import com.example.api.user.domain.BlockUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "BlockList", description = "Blocklist API")
public class BlockListController {
    private final AddBlockUsecase addBlockUsecase;
    private final GetListUsecase getListUsecase;
    private final ReleaseBlockUsecase releaseBlockUsecase;

    /**
     * 사용자 차단
     * @param addBlockDto (데이터)
     */
    @Operation(summary = "Block user", description = "사용자를 차단한다.")
    @PostMapping("/block")
    public void addBlockUser(@Valid @RequestBody AddBlockDto addBlockDto) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("BlockListController::addBlockUser: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        addBlockUsecase.addBlockUser(addBlockDto, securityUser.getUserId());
    }

    /**
     * 차단한 사용자 목록 조회
     * @param pageable (데이터)
     * @return blocked user list
     */
    @Operation(summary = "Get blocked user list", description = "차단한 사용자 목록을 조회한다.")
    @GetMapping("/block")
    public List<BlockUser> getBlockList(@PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC, size = 30) Pageable pageable) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("BlockListController::getBlockList: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return getListUsecase.getBlockList(pageable, securityUser.getUserId());
    }

    /**
     * 사용자 차단 해제
     * @param deleteBlockDto (데이터)
     */
    @Operation(summary = "Release blocked user", description = "사용자 차단을 해제한다.")
    @DeleteMapping("/block")
    public void releaseBlockUser(@Valid @RequestBody DeleteBlockDto deleteBlockDto) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("BlockListController::releaseBlockUser: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        releaseBlockUsecase.releaseBlockUser(deleteBlockDto, securityUser.getUserId());
    }
}