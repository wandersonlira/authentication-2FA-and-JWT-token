package br.com.studioSalon.apiAuthentication.repositories;

import br.com.studioSalon.apiAuthentication.model.ServicosModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicosRepository extends MongoRepository<ServicosModel, String> {
}
