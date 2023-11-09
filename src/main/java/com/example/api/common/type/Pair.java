package com.example.api.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pair<Type1, Type2> {
    private final Type1 first;
    private final Type2 second;
}