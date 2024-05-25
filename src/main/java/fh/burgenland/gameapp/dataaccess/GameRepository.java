package fh.burgenland.gameapp.dataaccess;

import fh.burgenland.gameapp.model.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
}
