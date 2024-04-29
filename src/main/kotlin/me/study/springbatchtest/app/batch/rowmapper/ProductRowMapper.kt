
import me.study.springbatchtest.app.batch.domain.ProductRequest
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class ProductRowMapper: RowMapper<ProductRequest> {

    override fun mapRow(rs: ResultSet, rowNum: Int): ProductRequest {
        return ProductRequest(
            id = rs.getLong("id"),
            name = rs.getString("name"),
            price = rs.getInt("price"),
            type = rs.getString("type")
        )
    }
}