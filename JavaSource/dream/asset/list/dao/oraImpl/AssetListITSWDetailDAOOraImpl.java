package dream.asset.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.asset.list.dao.AssetListITSWDetailDAO;
import dream.asset.list.dto.AssetListITSWDetailDTO;
import dream.asset.list.dto.AssetListITSWListDTO;

/**
 * Detail DAO implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="assetListITSWDetailDAOTarget"
 * @spring.txbn id="assetListITSWDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetListITSWDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements AssetListITSWDetailDAO
{
	
    public AssetListITSWDetailDTO findDetail(AssetListITSWListDTO assetListITSWListDTO, User user) 
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT                                    		");
		query.append("       x.sw_category      swCategoryId	    	");
		query.append("	   , x.eqitinstsw_id    eqItInstSwId            ");
		query.append("     ,(SELECT a.description FROM TACDUSRD a		");
		query.append("       WHERE 1=1									");
		query.append("       AND a.comp_no = '"+user.getCompNo()+"'		");
		query.append("       AND a.cdusrm_id = ( SELECT b.cdusrm_id FROM TACDUSRM b WHERE b.dir_type = 'SW_CATEGORY')		");
		query.append("		 AND a.cdusrd_no = x.sw_category	");
		query.append("        				  ) swCategory		");
		query.append("     , x.sw_name          swName		    ");
		query.append("     , x.sw_ver           swVer		    ");
		query.append("     , x.install_date     installDate	    ");
		query.append("     , x.remark           remark		    ");
		query.append("FROM TAEQITINSTSW x					    ");
		query.append("WHERE  1=1                                ");
        query.append("AND  x.comp_no            = ?             ");
        query.append("AND  x.equip_id       	= ?             ");
        query.append("AND  x.eqitinstsw_id      = ?             ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
              , assetListITSWListDTO.getItEqId()
              , assetListITSWListDTO.getEqItInstSwId()
        };
    
        AssetListITSWDetailDTO assetListITSWDetailDTO = 
        		(AssetListITSWDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new AssetListITSWDetailDTO()));
        
        return assetListITSWDetailDTO;
        
    }

    public int insertDetail(AssetListITSWDetailDTO assetListITSWDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	query.append(" INSERT INTO TAEQITINSTSW (       ");
    	query.append("  comp_no                        ");
    	query.append(", equip_id                       ");
    	query.append(", eqitinstsw_id                  ");
    	query.append(", sw_category                	   ");
    	query.append(", sw_name                 	   ");
    	query.append(", sw_ver                         ");
    	query.append(", install_date                   ");
    	query.append(", remark                         ");
    	query.append(") VALUES (                       ");
    	query.append("  ?                              ");
    	query.append(", ?                              ");
    	query.append(", ?                              ");
    	query.append(", ?                              ");
    	query.append(", ?                              ");
    	query.append(", ?                              ");
    	query.append(", ?                              ");
    	query.append(", ?                              ");
    	query.append(")                                ");

    	Object[] objects = new Object[] {
    			 user.getCompNo()
    			 , assetListITSWDetailDTO.getItEqId()
    			 , assetListITSWDetailDTO.getEqItInstSwId()
    			 , assetListITSWDetailDTO.getSwCategoryId()
    			 , assetListITSWDetailDTO.getSwName()
    			 , assetListITSWDetailDTO.getSwVer()
    			 , assetListITSWDetailDTO.getInstallDate()
    			 , assetListITSWDetailDTO.getRemark()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int updateDetail(AssetListITSWDetailDTO assetListITSWDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAEQITINSTSW SET			");
    	query.append("    sw_category           = ?     ");
    	query.append("  , sw_name               = ?  	");
    	query.append("  , sw_ver                = ?     ");
    	query.append("  , install_date          = ?     ");
    	query.append("  , remark                = ?		");
    	query.append("WHERE  comp_no			= ?		");
    	query.append("  AND  equip_id			= ?		");
    	query.append("  AND  eqitinstsw_id      = ?		");
    	
    	Object[] objects = new Object[] {
    			  assetListITSWDetailDTO.getSwCategoryId()
    			, assetListITSWDetailDTO.getSwName()
    			, assetListITSWDetailDTO.getSwVer()
    			, assetListITSWDetailDTO.getInstallDate()
    			, assetListITSWDetailDTO.getRemark()
    			, user.getCompNo()
    			, assetListITSWDetailDTO.getItEqId()
    			, assetListITSWDetailDTO.getEqItInstSwId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}