package com.github.a4ad.adapters.web.ui.controller.admin.server;

import com.github.a4ad.port.in.server.AddServerUseCase;
import com.github.a4ad.port.in.server.DeleteServerUseCase;
import com.github.a4ad.port.in.server.LoadServerQuery;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Server;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @startuml
 * !include ../../../../../../../../../../../docs/salt/serverOverview.activity.puml
 * @endumladdServer
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/server")
public class AdminServerController {

    final LoadServerQuery loadServerQuery;

    @GetMapping
    public String overview(Model model) {
        List<ServerWebModel> servers = loadServerQuery.loadServer().stream().map(this::map).collect(Collectors.toList());
        model.addAttribute("servers", servers);
        return "admin/server/overview";
    }

    private ServerWebModel map(LoadServerQuery.LoadServerQueryModel toMap) {
        return new ServerWebModel(toMap.getName(), toMap.getIp(), toMap.getPort());
    }



}
