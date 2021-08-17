package com.es.jd.esjd.controller;

import com.es.jd.esjd.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class ContentController {

    @Resource
    private ContentService contentService;

    @GetMapping("/parse/{keywords}")
    @ResponseBody
    public Boolean parse(@PathVariable String keywords) throws IOException {
        return contentService.parseContent(keywords);
    }

    @GetMapping("/search/{keyword}/{pageNo}/{pageSize}")
    @ResponseBody
    public List<Map<String, Object>> search(@PathVariable String keyword,
                                            @PathVariable int pageNo,
                                            @PathVariable int pageSize) throws IOException {
        return contentService.searchPage(keyword, pageNo, pageSize);
    }
}
