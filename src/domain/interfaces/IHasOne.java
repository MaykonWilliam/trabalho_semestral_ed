package domain.interfaces;

import utils.List;
import domain.relationships.OneToOneRelationship;

public interface IHasOne {
	List<OneToOneRelationship<?>> getOneToOneRelationships() throws Exception;
}
