package com.example.demo.controller;

import com.example.demo.bean.Message;
import com.example.demo.bean.Person;
import com.example.demo.service.DroolsService;
import com.example.demo.utils.DroolsUtil;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lind on 2020/8/17.
 */
@RestController
public class TestController {
    @Resource
    private DroolsService droolsService;

    @GetMapping("/age")
    public String truanAge(Integer age){

        //获取数据里的规则配置
        String r_age = droolsService.getValueByCode("R_AGE");


        String dlr = executeDlr(r_age, age);


        return dlr;
    }
    @GetMapping("/hello")
    public String getHello(){

        //获取数据里的规则配置
        String r_age = droolsService.getValueByCode("R_HELLO");


        String dlr = executeDlr(r_age);


        return dlr;
    }

    private String executeDlr(String r_rule,Integer age ){

        KieSession kSession = null;
        String result="";
        try {
            kSession = getKsion(r_rule);

            Person person = new Person("Kevin",age);
            kSession.insert(person);
            kSession.fireAllRules();
            System.out.println("处理结果：" + person.getBz());
            kSession.fireAllRules();
            result = person.getBz();
        }catch (Exception e){
            System.out.println("规则执行异常：{}"+e);
        }finally {
            if (null != kSession)
                kSession.dispose();
        }
        return result;
    }

    private KieSession getKsion(String r_rule){
        KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(ResourceFactory.newByteArrayResource(r_rule.getBytes()), ResourceType.DRL);
        // 检查规则正确性
        KnowledgeBuilderErrors errors = kb.getErrors();
        for (KnowledgeBuilderError error : errors) {
            System.out.println("规则文件正确性有误：{}" + error);
        }
        InternalKnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
        kBase.addPackages(kb.getKnowledgePackages());

        // 执行规则
        KieSession kieSession = kBase.newKieSession();
        return  kieSession;
    }

    private String executeDlr(String r_rule ){
        KieSession kSession = null;
        String result="";
        try {
            kSession = getKsion(r_rule);

            Message message = new Message();
            message.setMessage("Hello World");
            message.setStatus(Message.HELLO);


            kSession.insert(message);
            kSession.fireAllRules();

            kSession.fireAllRules();
            result = "已处理";
        }catch (Exception e){
            System.out.println("规则执行异常：{}"+e);
        }finally {
            if (null != kSession)
                kSession.dispose();
        }
        return result;
    }

    private void executeByFile(){
        try {
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();

            KieSession ksession = DroolsUtil.getKieSessionByName("ksession-rule");

            Person person = new Person("Kevin",12);
            ksession.insert(person);
            ksession.fireAllRules();
            System.out.println(person);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
