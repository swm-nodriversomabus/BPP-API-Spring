//package com.example.api.user.adapter.out.persistence;
//
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//
//@Entity
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name="social")
//public class SocialEntity {
//    @Id
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(nullable = false, name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
//    @ToString.Exclude
//    private UserEntity userId;
//
//
//
//}
