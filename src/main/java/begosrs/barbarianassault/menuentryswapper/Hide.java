package begosrs.barbarianassault.menuentryswapper;

import java.util.function.Predicate;
import java.util.function.Supplier;
import lombok.Value;

@Value
class Hide
{
	Predicate<String> optionPredicate;
	Predicate<String> targetPredicate;
	Supplier<Boolean> enabled;
	boolean strict;
}
