package database;

import com.jamshed.javaproject.database.Column;
import com.jamshed.javaproject.database.Row;
import com.jamshed.javaproject.database.Schema;
import com.jamshed.javaproject.database.Table;
import com.jamshed.javaproject.enums.DataType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    private Schema createUserSchema() {
        return new Schema(List.of(
                new Column("id", DataType.INT),
                new Column("name", DataType.TEXT),
                new Column("age", DataType.INT),
                new Column("active", DataType.BOOLEAN)
        ));
    }

    @Test
    void shouldInsertValidRow() {

        Schema schema = createUserSchema();
        Table table = new Table("users", schema);

        Row row = new Row();
        row.addValue(1);
        row.addValue("jamshed");
        row.addValue(26);
        row.addValue(true);

        table.insert(row);

        assertEquals(1, table.getRows().size());
    }

    @Test
    void shouldRejectRowWithIncorrectNumberOfValues() {

        Schema schema = createUserSchema();
        Table table = new Table("users", schema);

        Row row = new Row();
        row.addValue(1);
        row.addValue("jamshed");
        row.addValue(26);

        assertThrows(
                IllegalArgumentException.class,
                () -> table.insert(row)
        );

        assertEquals(0, table.getRows().size());
    }

    @Test
    void shouldRejectRowWithIncorrectDataType() {

        Schema schema = createUserSchema();
        Table table = new Table("users", schema);

        Row row = new Row();
        row.addValue(1);
        row.addValue("jamshed");

        // age expects INT, but BOOLEAN is provided
        row.addValue(true);

        row.addValue(true);

        assertThrows(
                IllegalArgumentException.class,
                () -> table.insert(row)
        );

        assertEquals(0, table.getRows().size());
    }

    @Test
    void shouldInsertMultipleValidRows() {

        Schema schema = createUserSchema();
        Table table = new Table("users", schema);

        Row row1 = new Row();
        row1.addValue(1);
        row1.addValue("jamshed");
        row1.addValue(26);
        row1.addValue(true);

        Row row2 = new Row();
        row2.addValue(2);
        row2.addValue("rahul");
        row2.addValue(30);
        row2.addValue(false);

        table.insert(row1);
        table.insert(row2);

        assertEquals(2, table.getRows().size());
    }

    @Test
    void shouldRejectNullRow() {

        Schema schema = createUserSchema();
        Table table = new Table("users", schema);

        assertThrows(
                IllegalArgumentException.class,
                () -> table.insert(null)
        );

        assertEquals(0, table.getRows().size());
    }

    @Test
    void shouldNotAllowModificationOfRowsThroughGetter() {

        Schema schema = createUserSchema();
        Table table = new Table("users", schema);

        Row row = new Row();
        row.addValue(1);
        row.addValue("jamshed");
        row.addValue(26);
        row.addValue(true);

        table.insert(row);

        assertEquals(1, table.getRows().size());

        assertThrows(
                UnsupportedOperationException.class,
                () -> table.getRows().clear()
        );

        // Table should still contain the row
        assertEquals(1, table.getRows().size());
    }
    @Test
    void shouldRejectNullTableName() {

        Schema schema = createUserSchema();

        assertThrows(
                IllegalArgumentException.class,
                () -> new Table(null, schema)
        );
    }
    @Test
    void shouldRejectBlankTableName() {

        Schema schema = createUserSchema();

        assertThrows(
                IllegalArgumentException.class,
                () -> new Table("", schema)
        );
    }
    @Test
    void shouldRejectWhitespaceOnlyTableName() {

        Schema schema = createUserSchema();

        assertThrows(
                IllegalArgumentException.class,
                () -> new Table("   ", schema)
        );
    }
    @Test
    void shouldRejectNullSchema() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Table("users", null)
        );
    }

    @Test
    void shouldConvertColumnNameToUpperCase() {
        Column column = new Column("userName", DataType.TEXT);

        assertEquals("USERNAME", column.getColumnName());
    }
    @Test
    void shouldConvertLowercaseColumnNameToUpperCase() {
        Column column = new Column("username", DataType.TEXT);

        assertEquals("USERNAME", column.getColumnName());
    }
    @Test
    void shouldKeepUppercaseColumnNameUppercase() {
        Column column = new Column("USERNAME", DataType.TEXT);

        assertEquals("USERNAME", column.getColumnName());
    }

    @Test
    void shouldRejectColumnNamesThatDifferOnlyByCase() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Schema(List.of(
                        new Column("username", DataType.TEXT),
                        new Column("USERNAME", DataType.TEXT)
                ))
        );
    }

    @Test
    void shouldNormalizeMixedCaseColumnName() {
        Column column = new Column("UsErNaMe", DataType.TEXT);

        assertEquals("USERNAME", column.getColumnName());
    }

    @Test
    void shouldNormalizeLowercaseTableName() {
        Table table = new Table("users", createUserSchema());

        assertEquals("USERS", table.getTableName());
    }

    @Test
    void shouldNormalizeMixedCaseTableName() {
        Table table = new Table("Users", createUserSchema());

        assertEquals("USERS", table.getTableName());
    }

    @Test
    void shouldKeepUppercaseTableNameUppercase() {
        Table table = new Table("USERS", createUserSchema());

        assertEquals("USERS", table.getTableName());
    }

    @Test
    void shouldNormalizeRandomCaseTableName() {
        Table table = new Table("uSeRs", createUserSchema());

        assertEquals("USERS", table.getTableName());
    }

    @Test
    void shouldNormalizeLowercaseColumnName() {
        Column column = new Column("username", DataType.TEXT);

        assertEquals("USERNAME", column.getColumnName());
    }

    @Test
    void shouldNormalizeMixedCaseColumnName1() {
        Column column = new Column("Username", DataType.TEXT);

        assertEquals("USERNAME", column.getColumnName());
    }

    @Test
    void shouldKeepUppercaseColumnNameUppercase1() {
        Column column = new Column("USERNAME", DataType.TEXT);

        assertEquals("USERNAME", column.getColumnName());
    }

    @Test
    void shouldNormalizeRandomCaseColumnName() {
        Column column = new Column("uSeRnAmE", DataType.TEXT);

        assertEquals("USERNAME", column.getColumnName());
    }
}