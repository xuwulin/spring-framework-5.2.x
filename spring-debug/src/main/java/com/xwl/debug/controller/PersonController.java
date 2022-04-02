package com.xwl.debug.controller;

import com.xwl.debug.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author xwl
 * @createdTime 2021/12/29 11:02
 * @description
 */
@Controller
public class PersonController {

	@Autowired
	private PersonService personService;
}
