package project.mgmt.presentation.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.mgmt.base.APIBaseTest;
import project.mgmt.domain.model.project_mgmt.project.Project;
import project.mgmt.infrastructure.persistence.hibernate.ProjectRepoJPA;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.mgmt.domain.model.project_mgmt.location.Location.CN;

public class ProjectControllerTest extends APIBaseTest {

    @Autowired
    private ProjectRepoJPA projectRepoJPA;

    @Test
    @Ignore
    //todo to finish
    public void should_return_empty_list_when_project_id_list_exists() throws Exception {
        //given
        Project project = new Project();
        project.setName("project name");
        project.setLocation(CN);
        project.setProjectManager("manager");
//        project.setClient();
//        project.setContract();
//        project.setSubProjects();

//        this.projectRepoJPA.save()

        Map<String, Set<String>> projectIdParams = Maps.newHashMap();
        projectIdParams.put("project id", Sets.newHashSet("sub project id"));

        //when
        String resultStr = this.mockMvc.perform(get("/projects/batch")
                .contentType(APPLICATION_JSON)
                .content(JSON.toJSONString(projectIdParams)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        //then
        assertTrue(JSON.parseObject(resultStr).isEmpty());
    }
}