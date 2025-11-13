package com.example.spring_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.SubjectEntity;
import com.example.demo.service.SubjectService;

/**
* 科目情報 Controller
*/
@Controller
public class SubjectController {

  /**
   * 科目情報 Service
   */
  //使用クラスのインスタンス化
  @Autowired
  SubjectService subjectService;

  /**
   * 科目情報一覧画面を表示
   * @param  model Model
   * @return  科目情報一覧画面のHTML
   */
  @GetMapping("/subject/list")
  public String subjectList(Model model) {
      //教科テーブルのデータを全て取得するメソッドを呼び出す。
      List<SubjectEntity> subjectlist = subjectService.searchAll();
      //取得した教科データの情報を画面側で利用できるようにmodelへ格納する。
      model.addAttribute("subjectlist", subjectlist);
      return "subject/list";
  }
}