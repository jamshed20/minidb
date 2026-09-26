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


}