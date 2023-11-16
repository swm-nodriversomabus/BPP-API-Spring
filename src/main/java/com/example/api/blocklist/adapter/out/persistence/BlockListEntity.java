package com.example.api.blocklist.adapter.out.persistence;

import com.example.api.common.entity.BaseEntity;
import com.example.api.user.adapter.out.persistence.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@IdClass(BlockListPK.class)
@Table(name="blocklist")
public class BlockListEntity extends BaseEntity {
    @Id
    private UUID userId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, name="blocklist_userid", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @ToString.Exclude
    @Id
    private UserEntity blocklistUserId;
}
