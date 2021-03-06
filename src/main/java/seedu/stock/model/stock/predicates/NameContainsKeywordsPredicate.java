package seedu.stock.model.stock.predicates;

import java.util.List;

import seedu.stock.model.stock.Stock;

/**
 * Tests that a {@code Stock}'s {@code Name} matches or contains
 * any of the keywords given.
 */
public class NameContainsKeywordsPredicate extends FieldContainsKeywordsPredicate {

    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns true if stock's name matches or contains all of the keywords.
     * @param stock stock to test
     * @return boolean true if stock matches keywords
     */
    @Override
    public boolean test(Stock stock) {
        String stockName = stock.getName().fullName.toLowerCase();

        if (!keywords.isEmpty() && keywords.stream().noneMatch(String::isEmpty)) {
            // test returns true if stock name contains all of the keywords specified
            return keywords.stream()
                    .allMatch(keyword -> stockName.contains(keyword.toLowerCase()));
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

    @Override
    public String toString() {
        return "Name: " + keywords.stream().reduce((keyword, next) -> keyword + " " + next).get();
    }
}
