package com.csys.workflow.factory;


import com.csys.workflow.domain.Workflow;
import com.csys.workflow.dto.WorkflowDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class WorkflowFactory {

    public static WorkflowDTO workflowToWorkflowDTO(Workflow workflow) {
        WorkflowDTO workflowDTO = new WorkflowDTO();
        workflowDTO.setIdWorkflow(workflow.getIdWorkflow());
        workflowDTO.setNomWorkflow(workflow.getNomWorkflow());
       // workflowDTO.setEtapeList(EtapeFactory.etapeToEtapeDTOs(workflow.getEtapeList()));
       //orkflowDTO.setInterface1(InterfaceFactory.interToInterfaceDTO(workflow.getInterface1()));
//       workflowDTO.setIdInterface1(InterfaceFactory.interToInterfaceDTO(workflow.getInterface1()).getIdInterface());
//        workflowDTO.setNomInterface1(InterfaceFactory.interToInterfaceDTO(workflow.getInterface1()).getNomInterface());
        return workflowDTO;
    }

    public static Workflow workflowDTOToWorkflow(WorkflowDTO workflowDTO) {
        Workflow workflow = new Workflow();
        workflow.setIdWorkflow(workflowDTO.getIdWorkflow());
        workflow.setNomWorkflow(workflowDTO.getNomWorkflow());
        //workflow.setInterface1(workflowDTO.getIdInterface1());
//        if (workflowDTO.getIdInterface1() != null) {
//            if (workflow.getInterface1() == null) {
//                workflow.setInterface1(new Interface()); // Assuming Interface is the class name
//            }
//            workflow.getInterface1().setIdInterface(workflowDTO.getIdInterface1());
//        }
        return workflow;
    }

    public static List<WorkflowDTO> workflowToWorkflowDTOs(List<Workflow> workflows) {
        List<WorkflowDTO> workflowsDTO = new ArrayList<>();
        workflows.forEach(x -> {
            workflowsDTO.add(workflowToWorkflowDTO(x));
        });
        return workflowsDTO;
    }
}
