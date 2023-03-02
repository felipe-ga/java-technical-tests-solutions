package com.gettrx.javatechnicaltestssolutions.web.controller;

import com.gettrx.javatechnicaltestssolutions.businnes.service.PeopleService;
import com.gettrx.javatechnicaltestssolutions.data.dto.People;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PeopleControllerTest {

    @Mock
    private PeopleService peopleService;

    @InjectMocks
    private PeopleController peopleController = new PeopleController();



}