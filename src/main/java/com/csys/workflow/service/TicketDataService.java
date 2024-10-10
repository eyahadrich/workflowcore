package com.csys.workflow.service;


import com.csys.workflow.domain.Demande;
import com.csys.workflow.domain.TicketData;
import com.csys.workflow.dto.TicketDataDTO;
import com.csys.workflow.factory.TicketDataFactory;
import com.csys.workflow.repository.DemandeRepository;
import com.csys.workflow.repository.TicketDataRepository;
import com.google.common.base.Preconditions;
import java.lang.Integer;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing TicketData.
 */
@Service
@Transactional
public class TicketDataService {

    private final Logger log = LoggerFactory.getLogger(TicketDataService.class);

    private final TicketDataRepository ticketdataRepository;
    private final DemandeRepository demandeRepository;

    public TicketDataService(TicketDataRepository ticketdataRepository, DemandeRepository demandeRepository) {
        this.ticketdataRepository = ticketdataRepository;
        this.demandeRepository = demandeRepository;
    }

    /**
     * Save a ticketdataDTO.
     *
     * @param ticketdataDTO
     * @return the persisted entity
     */
    public TicketDataDTO save(TicketDataDTO ticketdataDTO) {
        log.debug("Request to save TicketData: {}", ticketdataDTO);
        TicketData ticketdata = TicketDataFactory.ticketdataDTOToTicketData(ticketdataDTO);
        ticketdata = ticketdataRepository.save(ticketdata);
        TicketDataDTO resultDTO = TicketDataFactory.ticketdataToTicketDataDTO(ticketdata);
        return resultDTO;
    }
    public  boolean existsById(Integer id) {
        return ticketdataRepository.existsById(id);
    }

    /**
     * Update a ticketdataDTO.
     *
     * @param ticketdataDTO
     * @return the updated entity
     */
    public TicketDataDTO update(TicketDataDTO ticketdataDTO) {
        log.debug("Request to update TicketData: {}", ticketdataDTO);
        TicketData inBase = ticketdataRepository.findById(ticketdataDTO.getIdTicketData()).orElse(null);
        Preconditions.checkArgument(inBase != null, "ticketdata.NotFound");
        TicketData ticketdata = TicketDataFactory.ticketdataDTOToTicketData(ticketdataDTO);
        ticketdata = ticketdataRepository.save(ticketdata);
        TicketDataDTO resultDTO = TicketDataFactory.ticketdataToTicketDataDTO(ticketdata);
        return resultDTO;
    }

    /**
     * Get one ticketdataDTO by id.
     *
     * @param id the id of the entity
     * @return the entity DTO
     */
    @Transactional(
            readOnly = true
    )
    public TicketDataDTO findById(Integer id) {
        log.debug("Request to get TicketData: {}", id);
        TicketData ticketdata = ticketdataRepository.findById(id).orElse(null);
        TicketDataDTO dto = TicketDataFactory.ticketdataToTicketDataDTO(ticketdata);
        return dto;
    }

    /**
     * Get one ticketdata by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(
            readOnly = true
    )
    public TicketData findTicketData(Integer id) {
        log.debug("Request to get TicketData: {}", id);
        TicketData ticketdata = ticketdataRepository.findById(id).orElse(null);
        return ticketdata;
    }

    /**
     * Get all the ticketdatas.
     *
     * @return the the list of entities
     */
    @Transactional(
            readOnly = true
    )
    public List<TicketDataDTO> findAll() {
        log.debug("Request to get All TicketDatas");
        List<TicketData> result = ticketdataRepository.findAll();
        return TicketDataFactory.ticketdataToTicketDataDTOs(result);
    }

    /**
     * Delete ticketdata by id.
     *
     * @param id the id of the entity
     */
    public void delete(Integer id) {
        log.debug("Request to delete TicketData: {}", id);
        ticketdataRepository.deleteById(id);
    }
    @Transactional(
            readOnly = true
    )
    public List<TicketDataDTO> findTicketsDataByDemandId(Integer idDemande) {
        // Retrieve the Demand object corresponding to the given ID
        Demande demande = demandeRepository.findById(idDemande).orElse(null);

        // If the Demand object is found, use it to find the associated tickets
        if (demande != null) {
            // Retrieve the list of tickets associated with the given demand
            List<TicketData> ticketsData = ticketdataRepository.findByDemande(demande);

            // Convert the list of Ticket entities to TicketDTOs
            return TicketDataFactory.ticketdataToTicketDataDTOs(ticketsData);
        } else {
            // Handle the case where the Demand object with the given ID is not found
            // For example, you could return an empty list or throw an exception
            return Collections.emptyList();
        }
    }
}
