package org.rest;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.service.AlticciService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.util.ArrayList;


@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Path("/alticci")
public class AlticciRest {

    @Inject
    AlticciService alticciService;

    @Operation(summary = "Retorna o valor pelo índice")
    @APIResponse(responseCode = "200", //
            description = "Retorna o valor pelo índice passado pelo usuário, que deve ser um número natural (inteiro e positivo, incluindo o zero)", //
            content = @Content(//
                    mediaType = MediaType.APPLICATION_JSON))
    @APIResponse(responseCode = "400", //
            description = "Erro retornado pro usuário caso o índice não seja um número natural (inteiro e positivo, incluindo o zero)", //
            content = @Content(//
                    mediaType = MediaType.APPLICATION_JSON))
    @GET
    @Path("/{n}")
    public Response getAlticciSeqByIndex(@PathParam("n") int n) {
        try {
            BigInteger response = alticciService.getAlticciSeqByIndex(n);
            return Response.ok(response).build();
        } catch (IllegalArgumentException e) {
            e.getLocalizedMessage();
            return Response.status(400)
                    .entity("O índice deve ser um valor natural (inteiro e positivo, incluindo o zero)!")
                    .build();
        }
    }

    @Operation(summary = "Retorna um array de valores com os índices inicio e fim")
    @APIResponse(responseCode = "200", //
            description = "Retorna um array de valores com os índices inicio e fim passados pelo usuário," +
                    " que devem ser números naturais (inteiro e positivo, incluindo o zero) e o início não pode ser maior que o fim", //
            content = @Content(//
                    mediaType = MediaType.APPLICATION_JSON))
    @APIResponse(responseCode = "400", //
            description = "Erro retornado pro usuário caso os índices não sejam números naturais (inteiro e positivo, incluindo o zero) e/ou" +
                    " o início não foi menor ou igual ao fim", //
            content = @Content(//
                    mediaType = MediaType.APPLICATION_JSON))
    @GET
    @Path("/array")
    public Response getAlticciSeqByIndex(@QueryParam("start") int start, @QueryParam("end") int end) {
        try {
            ArrayList response = alticciService.getAlticciSeqArray(start, end);
            return Response.ok(response).build();
        } catch (IllegalArgumentException e) {
            e.getLocalizedMessage();
            return Response.status(400)
                    .entity("Os valores devem ser inteiros e positivos com o final maior que o início!")
                    .build();
        }
    }
}
