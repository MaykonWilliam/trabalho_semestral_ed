package domain.interfaces;

import utils.List;
import domain.relationships.BelongsToRelationship;

public interface IBelongsTo {
	List<BelongsToRelationship<?>> getBelongsToRelationships() throws Exception;
}
