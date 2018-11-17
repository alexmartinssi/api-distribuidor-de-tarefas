package com.br.apipadrao.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.apipadrao.domain.Register;
import com.br.apipadrao.domain.Task;
import com.br.apipadrao.domain.User;
import com.br.apipadrao.enums.Profile;
import com.br.apipadrao.repositories.RegisterRepository;
import com.br.apipadrao.repositories.TaskRepository;
import com.br.apipadrao.repositories.UserRepository;

@Service
public class DBService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void instantiateTestDatabase() throws ParseException {

        //Users
        User user1 = new User(null,"Administrador", "alexmb000@hotmail.com","admin",passwordEncoder.encode("123"),true);
        user1.addProfile(Profile.ADMIN);
        User user2 = new User(null,"Jaqueline", "jaquetgn@gmail.com","jaquetg",passwordEncoder.encode("123"),true);

        //Registers
        DateFormat f = DateFormat.getDateInstance();
        Date initialDate = f.parse("12/11/2018 09:00:00");
        Date finalDate = f.parse("15/11/2018 00:00:00");        
        Register register1 = new Register(null,"Atividades Domésticas", initialDate, finalDate, "Fim de semana sem fazer nada na casa");
        
        Date initialDate2 = f.parse("18/11/2018 09:00:00");
        Date finalDate2 = f.parse("23/11/2018 00:00:00");        
        Register register2 = new Register(null,"Pagar Contas", initialDate2, finalDate2, "Livre de pagar a próxima conta");
        
        
        //Tasks
        Task task1 = new Task(null,"Lavar a louça, enxugar a louça e guarda a louça","Limpar louças",user1,register1);
        Task task2 = new Task(null,"Varrer e passar pano","Limpar o chão da casa",user2,register1);
        Task task3 = new Task(null,"Paga a conta de água e de luz","Pagar contas fixas",user1,register2);
        
        taskRepository.saveAll(Arrays.asList(task1, task2, task3));
        userRepository.saveAll(Arrays.asList(user1, user2));
        registerRepository.saveAll(Arrays.asList(register1,register2));
        
        
    }
}
