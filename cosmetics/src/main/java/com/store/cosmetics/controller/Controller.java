package com.store.cosmetics.controller;

import com.store.cosmetics.utils.OutMessage;
import com.store.cosmetics.utils.PageableUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class Controller extends PageableUtils {
    @Autowired
    public OutMessage message;
}
