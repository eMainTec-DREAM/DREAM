package mobile.dream.asset.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import mobile.dream.asset.list.dao.AssetListLovListDAO;
import mobile.dream.asset.list.dto.AssetListLovListDTO;

/**
 * 설비 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="assetListLovListDAOTarget"
 * @spring.txbn id="assetListLovListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetListLovListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssetListLovListDAO
{
    /**
     * 설비 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetListLovListDTO
     * @param loginUser
     * @return
     */
    public List findEquipList(AssetListLovListDTO assetListLovListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT															");
        query.append("		x.equip_id									equipId,		");
        query.append("		x.item_no									itemNo,			");
        query.append("		x.description								equipDesc,		");
        query.append("		(SELECT full_desc											");
        query.append("		   FROM TAEQLOC												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND eqloc_id = x.eqloc_id)				eqLocDesc,		");
        query.append("		(SELECT full_desc											");
        query.append("		   FROM TAEQCTG												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND eqctg_id = x.eqctg_id)				eqCtgDesc,		");
        query.append("		x.maker										maker,			");
        query.append("		x.model_no									modelNo,		");
        query.append("		dbo.SFACODE_TO_DESC(x.plf_type,'PLF_TYPE','SYS','','"+loginUser.getLangId()+"')	plfTypeDesc,");
        query.append("		 x.is_law_eq								isLawEq,		");
        query.append("		(SELECT description											");
        query.append("		   FROM TADEPT												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND dept_id = x.dept_id)				deptDesc,		");
        query.append("		(SELECT emp_name											");
        query.append("		   FROM TAEMP												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND emp_id = x.main_mng_id)				mainMngName,	");
        query.append("		(SELECT emp_name											");
        query.append("		   FROM TAEMP												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND emp_id = x.sub_mng_id)				subMngName,		");
        query.append("		x.eqloc_id									eqLocId,			");
        query.append("      dbo.SFACODE_TO_DESC(x.eq_status,'EQ_STATUS','SYS','','"+loginUser.getLangId()+"') eqstatus    ");
        query.append("FROM   TAEQUIPMENT x												");
        query.append("WHERE 1=1															");
        query.append(this.getWhere(assetListLovListDTO, loginUser));
        query.append("ORDER BY   ord_no													");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    private String getWhere(AssetListLovListDTO assetListLovListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = loginUser.getCompNo();

        query.getAndQuery("x.comp_no", compNo);
        query.getAndQuery("x.item_no", assetListLovListDTO.getItemNo());
        query.getLikeQuery("x.description", assetListLovListDTO.getEquipDesc());
        query.getLikeQuery("x.is_law_eq", assetListLovListDTO.getIsLawEq());
        query.getLikeQuery("x.eq_status", assetListLovListDTO.getEqStatusId());
        
        //위치
        query.getEqLocLevelQuery("x.eqloc_id", assetListLovListDTO.getEqLocId(), assetListLovListDTO.getEqLocDesc(), compNo);
        //종류
        query.getEqCtgLevelQuery("x.eqctg_id", assetListLovListDTO.getEqCtgId(), assetListLovListDTO.getEqCtgDesc(), compNo);
        query.getSysCdQuery("x.plf_type", assetListLovListDTO.getPlfTypeId(), assetListLovListDTO.getPlfTypeDesc(), "PLF_TYPE", compNo,loginUser.getLangId());
        query.getCodeLikeQuery("x.main_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.main_mng_id AND a.comp_no='"+compNo+"')", 
        		assetListLovListDTO.getMainMngId(), assetListLovListDTO.getMainMngName());
        query.getCodeLikeQuery("x.sub_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.sub_mng_id AND a.comp_no='"+compNo+"')", 
        		assetListLovListDTO.getSubMngId(), assetListLovListDTO.getSubMngName());
        query.getDeptLevelQuery("x.dept_id", assetListLovListDTO.getDeptId(), assetListLovListDTO.getDeptDesc(), compNo);
        
        return query.toString();
    }
}