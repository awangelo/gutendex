package com.example.gutendex.service;

public interface IConvertData {
    <T> T getData(String json, Class<T> tClass);
}
