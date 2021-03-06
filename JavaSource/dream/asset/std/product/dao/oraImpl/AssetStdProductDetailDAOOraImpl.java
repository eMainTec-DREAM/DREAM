package dream.asset.std.product.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.asset.std.product.dao.AssetStdProductDetailDAO;
import dream.asset.std.product.dto.AssetStdProductDetailDTO;

/**
 * 생산품목 - 상세 dao
 * 
 * @author ghlee
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="assetStdProductDetailDAOTarget"
 * @spring.txbn id="assetStdProductDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetStdProductDetailDAOOraImpl extends BaseJdbcDaoSupportOra
		implements AssetStdProductDetailDAO {
	/**
	 * detail find
	 * 
	 * @author ghlee
	 * @version $Id: $
	 * @since 1.0
	 * 
	 * @param productNo
	 * @return
	 */
	public AssetStdProductDetailDTO findDetail(User user, String productId) {
		QueryBuffer query = new QueryBuffer();

		query.append("SELECT        ");
		query.append("    x.product_id                  productId       ");
		query.append("    ,x.product_no                 productNo       ");
		query.append("    ,x.description              description       ");
		query.append("    ,x.is_use                    isUse            ");
		query.append("    ,x.REMARK               REMARK                ");
		query.append("FROM TAPRODUCT x                                  ");
		query.append("WHERE  1=1                                        ");
		query.append("   and x.comp_no = ?                              ");
		query.append("   and x.product_id = ?                           ");

		Object[] objects = new Object[] { 
				user.getCompNo()
				, productId 
		};

		AssetStdProductDetailDTO assetStdProductDetailDTO = (AssetStdProductDetailDTO) getJdbcTemplate()
				.query(query.toString(), objects,
						new MwareExtractor(new AssetStdProductDetailDTO()));

		return assetStdProductDetailDTO;
	}

	/**
	 * detail insert
	 * 
	 * @author ghlee
	 * @version $Id: $
	 * @since 1.0
	 * 
	 * @param assetStdProductDetailDTO
	 * @return
	 */
	public int insertDetail(AssetStdProductDetailDTO assetStdProductDetailDTO,
			User user) {
		QueryBuffer query = new QueryBuffer();

		query.append("INSERT INTO TAPRODUCT (        ");
		query.append("    comp_no,       product_id,       product_no,      ");
		query.append("    description,    is_use,          remark     ");
		query.append("    )VALUES(      ");
		query.append("    ?,                ?,                  ?,        ");
		query.append("    ?,                ?,                  ?        ");
		query.append("    )                                                 ");

		Object[] objects = new Object[] { user.getCompNo(),
				assetStdProductDetailDTO.getProductId(),
				assetStdProductDetailDTO.getProductNo(),
				assetStdProductDetailDTO.getDescription(),
				assetStdProductDetailDTO.getIsUse(),
				assetStdProductDetailDTO.getRemark() };

		return getJdbcTemplate().update(query.toString(), objects);
	}

	/**
	 * detail update
	 * 
	 * @author ghlee
	 * @version $Id: $
	 * @since 1.0
	 * 
	 * @param assetStdProductDetailDTO
	 * @return
	 */
	public int updateDetail(AssetStdProductDetailDTO assetStdProductDetailDTO,
			User user) {
		QueryBuffer query = new QueryBuffer();

		query.append("UPDATE TAPRODUCT SET       ");
		query.append("    product_no        = ?       ");
		query.append("    ,description    = ?       ");
		query.append("    ,is_use          = ?      ");
		query.append("    ,REMARK       = ?     ");
		query.append("WHERE 1=1     ");
		query.append("AND  comp_no        = ?             ");
		query.append("AND  product_id        = ?	            ");

		Object[] objects = new Object[] {
				assetStdProductDetailDTO.getProductNo(),
				assetStdProductDetailDTO.getDescription(),
				assetStdProductDetailDTO.getIsUse(),
				assetStdProductDetailDTO.getRemark(), user.getCompNo(),
				assetStdProductDetailDTO.getProductId() };

		return getJdbcTemplate().update(query.toString(), objects);
	}

	/**
	 * valid assetNo
	 * 
	 * @author ghlee
	 * @version $Id: $
	 * @since 1.0
	 * 
	 * @param assetStdProductDetailDTO
	 * @return
	 */
	public String validProductNo(
			AssetStdProductDetailDTO assetStdProductDetailDTO, User user) {

		QueryBuffer query = new QueryBuffer();

		query.append("SELECT COUNT(*)                        ");
		query.append("FROM   TAPRODUCT x                        ");
		query.append("WHERE 1=1 ");
		query.getAndQuery("x.comp_no", user.getCompNo());
		query.getAndQuery("x.product_no",
				assetStdProductDetailDTO.getProductNo());
		query.append("AND x.product_id != "
				+ assetStdProductDetailDTO.getProductId());

		return QueryBuffer.listToString(getJdbcTemplate().queryForList(
				query.toString()));

	}
}