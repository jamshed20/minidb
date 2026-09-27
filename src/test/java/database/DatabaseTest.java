package database;

import com.jamshed.javaproject.database.Column;
import com.jamshed.javaproject.database.Database;
import com.jamshed.javaproject.database.Schema;
import com.jamshed.javaproject.database.Table;
import com.jamshed.javaproject.enums.DataType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    private Schema createUserSchema() {
        return new Schema(List.of(
                new Column("id", DataType.INT),
                new Column("name", DataType.TEXT),
                new Column("age", DataType.INT),
                new Column("active", DataType.BOOLEAN)
        ));
    }

    private Schema createProductSchema() {
        return new Schema(List.of(
                new Column("id", DataType.INT),
                new Column("name", DataType.TEXT),
                new Column("price", DataType.INT),
                new Column("available", DataType.BOOLEAN)
        ));
    }

    private Schema createOrderSchema() {
        return new Schema(List.of(
                new Column("id", DataType.INT),
                new Column("userId", DataType.INT),
                new Column("productId", DataType.INT),
                new Column("quantity", DataType.INT)
        ));
    }

    @Test
    void testDatabase() {
        Database database = new Database();
        assertNotNull(database);

        Table users = new Table("users", createUserSchema());

        assertDoesNotThrow(() -> database.createTable(users));
        assertEquals(users, database.getTable("users"));
        assertEquals(users, database.getTable("Users"));
        assertEquals(users, database.getTable("USERS"));
        assertEquals(users, database.getTable("uSeRs"));

        assertThrows(IllegalArgumentException.class, ()-> database.getTable("orders"));
    }

    @Test
    void testCreateDropGetTable() {
        Database database = new Database();
        assertNotNull(database);

        Table users = new Table("users", createUserSchema());
        assertDoesNotThrow(() -> database.createTable(users));
        assertEquals(users, database.getTable("users"));
        assertDoesNotThrow(()->database.dropTable("users"));
        assertThrows(IllegalArgumentException.class, ()-> database.getTable("users"));
    }
    @Test
    void testDropTable() {
        Database database = new Database();
        assertNotNull(database);

        assertThrows(IllegalArgumentException.class, ()-> database.dropTable("users"));

    }

    @Test
    void shouldRejectNullTableWhenCreatingTable() {
        Database database = new Database();

        assertThrows(
                IllegalArgumentException.class,
                () -> database.createTable(null)
        );
    }

    @Test
    void shouldRejectNullTableNameWhenGettingTable() {
        Database database = new Database();

        assertThrows(
                IllegalArgumentException.class,
                () -> database.getTable(null)
        );
    }

    @Test
    void shouldRejectEmptyTableNameWhenGettingTable() {
        Database database = new Database();

        assertThrows(
                IllegalArgumentException.class,
                () -> database.getTable("")
        );
    }

    @Test
    void shouldRejectBlankTableNameWhenGettingTable() {
        Database database = new Database();

        assertThrows(
                IllegalArgumentException.class,
                () -> database.getTable("   ")
        );
    }

    @Test
    void shouldRejectNullTableNameWhenDroppingTable() {
        Database database = new Database();

        assertThrows(
                IllegalArgumentException.class,
                () -> database.dropTable(null)
        );
    }

    @Test
    void shouldRejectEmptyTableNameWhenDroppingTable() {
        Database database = new Database();

        assertThrows(
                IllegalArgumentException.class,
                () -> database.dropTable("")
        );
    }

    @Test
    void shouldRejectBlankTableNameWhenDroppingTable() {
        Database database = new Database();

        assertThrows(
                IllegalArgumentException.class,
                () -> database.dropTable("   ")
        );
    }

    @Test
    void shouldRejectDuplicateTableNameIgnoringCase() {
        Database database = new Database();

        Table users1 = new Table("users", createUserSchema());
        Table users2 = new Table("USERS", createUserSchema());

        database.createTable(users1);

        assertThrows(
                IllegalArgumentException.class,
                () -> database.createTable(users2)
        );
    }

    @Test
    void shouldStoreMultipleTables() {
        Database database = new Database();

        Table users = new Table("users", createUserSchema());
        Table products = new Table("products", createProductSchema());
        Table orders = new Table("orders", createOrderSchema());

        database.createTable(users);
        database.createTable(products);
        database.createTable(orders);

        assertEquals(users, database.getTable("users"));
        assertEquals(products, database.getTable("products"));
        assertEquals(orders, database.getTable("orders"));
    }

    @Test
    void shouldDropTableIgnoringCase() {
        Database database = new Database();

        Table users = new Table("users", createUserSchema());
        database.createTable(users);

        assertDoesNotThrow(() -> database.dropTable("uSeRs"));

        assertThrows(
                IllegalArgumentException.class,
                () -> database.getTable("USERS")
        );
    }

}