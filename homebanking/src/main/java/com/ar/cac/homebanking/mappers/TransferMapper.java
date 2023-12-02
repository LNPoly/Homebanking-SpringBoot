package com.ar.cac.homebanking.mappers;

import com.ar.cac.homebanking.models.Transfer;
import com.ar.cac.homebanking.models.dtos.TransferDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransferMapper {

    public Transfer dtoToTransfer(TransferDTO dto){
        return Transfer.builder()
                .transferAmount(dto.getTransferAmount())
                .date(dto.getDate())
                .originAccount(dto.getOriginAccount())
                .targetAccount(dto.getTargetAccount())
                .build();
    }

    public TransferDTO transferToDto(Transfer transfer){
        return TransferDTO.builder()
                .transferId(transfer.getTransferId())
                .transferAmount(transfer.getTransferAmount())
                .targetAccount(transfer.getTargetAccount())
                .originAccount(transfer.getOriginAccount())
                .date(transfer.getDate())
                .build();
    }
}
