package com.emep.zaixian.controller;

import java.net.URI;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.emep.zaixian.model.Article;
import com.emep.zaixian.service.ArticleService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/Article")
public class ArticleController {
	@Autowired
    private ArticleService articleService;
	
	@RequestMapping("/allNews")
	String getAddArticle() {
		return "/allNews";
	}
	
	@RequestMapping(value="/all",method=RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@RequiresPermissions("user:view")
	public @ResponseBody
    String list(Model model) {
        Gson gson = new Gson();
		return gson.toJson(articleService.findAll());
    }
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Article>> listAllArticles() {
        List<Article> articles = articleService.findAll();
        if(articles.isEmpty()){
            return new ResponseEntity<List<Article>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Article>>(articles, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/article/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getArticle(@PathVariable("id") long id,Model model) {
        System.out.println("Fetching Article with id " + id);
        Article article = articleService.findById(id);
        Long nextId = articleService.getNextById(id);
        Long prevId = articleService.getPrevById(id);
        if (article == null) {
            System.out.println("Article with id " + id + " not found");
            return new ResponseEntity<Article>(HttpStatus.NOT_FOUND).toString();
        }
        model.addAttribute("article", article);
        model.addAttribute("nextId",nextId);
        model.addAttribute("prevId",prevId);
        return "/News";
    }
	
	@RequiresPermissions("user:create")
	@RequestMapping(value = "/create/", method = RequestMethod.POST)
    public ResponseEntity<Void> createArticle(@RequestBody Article article,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Article " + article.getTitle());
        if (articleService.isArticleExist(article)) {
            System.out.println("A Article with name " + article.getTitle() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        articleService.createArticle(article);
        HttpHeaders headers = new HttpHeaders();
        URI location = ucBuilder.path("/Article/article/{id}").buildAndExpand(article.getId()).toUri();
        headers.setLocation(location);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}
