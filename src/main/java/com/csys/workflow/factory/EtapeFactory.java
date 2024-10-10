package com.csys.workflow.factory;


import com.csys.workflow.domain.Etape;
import com.csys.workflow.domain.Workflow;
import com.csys.workflow.dto.EtapeDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class EtapeFactory {

    public static EtapeDTO etapeToEtapeDTO(Etape etape) {
        EtapeDTO etapeDTO = new EtapeDTO();
        etapeDTO.setIdEtape(etape.getIdEtape());
        etapeDTO.setRoleEtape(etape.getRoleEtape());
        etapeDTO.setNomEtape(etape.getNomEtape());
        etapeDTO.setOrdre(etape.getOrdre());
        //etapeDTO.setValidationList(ValidationFactory.validationToValidationDTOs(etape.getValidationList()));
        etapeDTO.setIdWorkflow(WorkflowFactory.workflowToWorkflowDTO(etape.getWorkflow()).getIdWorkflow());
        etapeDTO.setNomWorkflow(WorkflowFactory.workflowToWorkflowDTO(etape.getWorkflow()).getNomWorkflow());

        return etapeDTO;
    }

    public static Etape etapeDTOToEtape(EtapeDTO etapeDTO) {
        Etape etape = new Etape();
        etape.setIdEtape(etapeDTO.getIdEtape());
        etape.setRoleEtape(etapeDTO.getRoleEtape());
        etape.setNomEtape(etapeDTO.getNomEtape());
        etape.setOrdre(etapeDTO.getOrdre());
        // Ensure that the workflow is not null before setting its ID
            Workflow workflow = new Workflow();
//            Interface interface1=new Interface();
//            workflow.setInterface1(interface1);
            workflow.setIdWorkflow(etapeDTO.getIdWorkflow());
            etape.setWorkflow(workflow);

        return etape;
    }

    public static List<EtapeDTO> etapeToEtapeDTOs(List<Etape> etapes) {
        List<EtapeDTO> etapesDTO = new ArrayList<>();
        etapes.forEach(x -> {
            etapesDTO.add(etapeToEtapeDTO(x));
        });
        return etapesDTO;
    }
}
