package com.github.a4ad.adapters.web.ui.controller.admin.server;

import com.github.a4ad.port.in.server.AddServerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/server/new")
public class NewServerController {

    final AddServerUseCase addServerUseCase;

    @GetMapping
    public String prepareNewServer(Model model) {
        model.addAttribute("serverWebModel", ServerWebModel.withDefaultPort());
        return "admin/server/new";
    }

    @PostMapping
    public String saveNewServer(Model model, ServerWebModel serverWebModel) {
        if (addServerUseCase.addServer(new AddServerUseCase.AddServerUseCaseCommand(serverWebModel.name, serverWebModel.ip, serverWebModel.port))) {
            return "redirect:/admin/server";
        } else {
            model.addAttribute("serverWebModel", serverWebModel);
            return "admin/server/new";
        }
    }


}
