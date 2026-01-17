package com.cosmetics.store.controller;

import com.cosmetics.store.utils.OutMessage;
import com.cosmetics.store.utils.PageableUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class Controller extends PageableUtils {
    @Autowired
    public OutMessage message;
}
