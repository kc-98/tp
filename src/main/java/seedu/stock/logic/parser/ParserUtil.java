package seedu.stock.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.LinkedHashSet;
import java.util.Set;

import seedu.stock.commons.core.Messages;
import seedu.stock.commons.core.index.Index;
import seedu.stock.commons.util.StringUtil;
import seedu.stock.logic.parser.exceptions.ParseException;
import seedu.stock.model.stock.Location;
import seedu.stock.model.stock.Name;
import seedu.stock.model.stock.Quantity;
import seedu.stock.model.stock.QuantityAdder;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Source;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String quantity} into a {@code Quantity}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code quantity} is invalid.
     */
    public static Quantity parseQuantity(String quantity) throws ParseException {
        requireNonNull(quantity);
        String trimmedQuantity = quantity.trim();
        if (!Quantity.isValidQuantity(trimmedQuantity)) {
            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS);
        }
        return new Quantity(trimmedQuantity);
    }

    /**
     * Parses a {@code String location} into an {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        requireNonNull(location);
        String trimmedLocation = location.trim();
        if (!Location.isValidLocation(trimmedLocation)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(trimmedLocation);
    }

    /**
     * Parses a {@code String source} into an {@code Source}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code source} is invalid.
     */
    public static Source parseSource(String source) throws ParseException {
        requireNonNull(source);
        String trimmedSource = source.trim();
        if (!Source.isValidSource(trimmedSource)) {
            throw new ParseException(Source.MESSAGE_CONSTRAINTS);
        }
        return new Source(trimmedSource);
    }

    /**
     * Parses a {@code String serialNumber} into an {@code SerialNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code serialNumber} is invalid.
     */
    public static Set<SerialNumber> parseSerialNumber(String serialNumber) throws ParseException {
        requireNonNull(serialNumber);
        String trimmedSerialNumber = serialNumber.trim();
        String[] withoutPrefix = trimmedSerialNumber.split("sn/");
        //a valid array after splitting should be at length 2, index 0 being an empty string and 1
        //being the actual serial number.
        if (withoutPrefix.length < 2) {
            throw new ParseException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }
        Set<SerialNumber> serialNumberSet = new LinkedHashSet<>();

        for (int i = 1; i < withoutPrefix.length; i++) {
            String currentSerialNumberInString = withoutPrefix[i];
            if (!SerialNumber.isValidSerialNumber(currentSerialNumberInString)) {
                throw new ParseException(SerialNumber.MESSAGE_CONSTRAINTS);
            }
            serialNumberSet.add(new SerialNumber(currentSerialNumberInString.trim()));
        }
        return serialNumberSet;
    }

    /**
     * Parses {@code valueToBeAdded} into a {@code QuantityAdder}.
     *
     * @param valueToBeAdded The value to be added into a certain quantity.
     * @return A new quantity adder containing the value to be added.
     * @throws ParseException If there are parsing errors.
     */
    public static QuantityAdder parseQuantityAdder(String valueToBeAdded) throws ParseException {
        requireNonNull(valueToBeAdded);
        String trimmedValue = valueToBeAdded.trim();
        if (!QuantityAdder.isValidValue(trimmedValue)) {
            throw new ParseException(QuantityAdder.MESSAGE_CONSTRAINTS);
        }
        return new QuantityAdder(trimmedValue);
    }
}