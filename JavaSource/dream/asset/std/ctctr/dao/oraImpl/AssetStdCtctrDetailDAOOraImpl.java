package dream.asset.std.ctctr.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.asset.std.ctctr.dao.AssetStdCtctrDetailDAO;
import dream.asset.std.ctctr.dto.AssetStdCtctrDetailDTO;

/**
 * CostCenter - »ó¼¼ dao
 * 
 * @author ghlee
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="assetStdCtctrDetailDAOTarget"
 * @spring.txbn id="assetStdCtctrDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetStdCtctrDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements AssetStdCtctrDetailDAO
{
    /**
     * detail find
     * @author ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param ctctrNo
     * @return
     */
    public AssetStdCtctrDetailDTO findDetail(User user, String ctctrId)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        ");
        query.append("    x.ctctr_id                  ctctrId       ");
        query.append("    ,x.ctctr_no                 ctctrNo       ");
        query.append("    ,x.description              description   ");
        query.append("    ,x.is_use                   isUseId       ");
        query.append("    ,SFACODE_TO_DESC( x.is_use, 'IS_USE','SYS',x.comp_no, ?)											isUse  		");
        query.append("    ,x.plant                    plant         ");
        query.append("    ,(SELECT description FROM TAPLANT WHERE comp_no=x.comp_no AND plant=x.plant)						plantDesc 	");
        query.append("    ,x.REMARK                   REMARK        ");
        query.append("    ,x.dept_id                  deptId       ");
        query.append("    ,(SELECT a.description FROM TADEPT a WHERE a.comp_no=x.comp_no AND a.dept_id=x.dept_id)			deptDesc 	");
        query.append("	  ,x.in_wcode_id    		  wcodeId		");
        query.append("    ,(select a.wname from TAWAREHOUSE a where a.comp_no = x.comp_no and a.wcode_id = x.in_wcode_id)	wcodeDesc	");
        query.append("FROM TACTCTR x                                ");
        query.append("WHERE  1=1                                    ");
        query.append("  and x.comp_no = ?                           ");
        query.append("  and x.ctctr_id = ?                          ");
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,user.getCompNo()
    			,ctctrId
    	};
    
        AssetStdCtctrDetailDTO assetStdCtctrDetailDTO = 
        		(AssetStdCtctrDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new AssetStdCtctrDetailDTO()));

        
        return assetStdCtctrDetailDTO;
    }
    
    /**
     * detail insert
     * @author ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdCtctrDetailDTO
     * @return
     */
    public int insertDetail(AssetStdCtctrDetailDTO assetStdCtctrDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TACTCTR (        ");
    	query.append("    comp_no,       ctctr_id,       ctctr_no,    ");
    	query.append("    description,    is_use,          remark,    ");
        query.append("    plant,         dept_id,		 in_wcode_id  ");
    	query.append("    )VALUES(      							  ");
    	query.append("    ?,                ?,                  ?,    ");
    	query.append("    ?,                ?,                  ?,    ");
    	query.append("    ?,				?,					?	  ");
    	query.append("    )                                           ");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,assetStdCtctrDetailDTO.getCtctrId()
    			,assetStdCtctrDetailDTO.getCtctrNo()
    			,assetStdCtctrDetailDTO.getDescription()
    			,assetStdCtctrDetailDTO.getIsUseId()
    			,assetStdCtctrDetailDTO.getRemark()
    			,assetStdCtctrDetailDTO.getPlant()
    			,assetStdCtctrDetailDTO.getDeptId()
                ,assetStdCtctrDetailDTO.getWcodeId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail update
     * @author ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdCtctrDetailDTO
     * @return
     */
    public int updateDetail(AssetStdCtctrDetailDTO assetStdCtctrDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TACTCTR SET           ");
        query.append("    ctctr_no        = ?      ");
        query.append("    ,description    = ?      ");
        query.append("    ,is_use         = ?      ");
        query.append("    ,plant          = ?      ");
        query.append("    ,dept_id        = ?      ");
        query.append("    ,REMARK         = ?      ");
        query.append("    ,in_wcode_id    = ?      ");
        query.append("WHERE 1=1                    ");
        query.append("AND  comp_no        = ?      ");
        query.append("AND  ctctr_id       = ?      ");
        
        Object[] objects = new Object[] {
                assetStdCtctrDetailDTO.getCtctrNo()
                ,assetStdCtctrDetailDTO.getDescription()
                ,assetStdCtctrDetailDTO.getIsUseId()
                ,assetStdCtctrDetailDTO.getPlant()
                ,assetStdCtctrDetailDTO.getDeptId()
                ,assetStdCtctrDetailDTO.getRemark()
                ,assetStdCtctrDetailDTO.getWcodeId()
                ,user.getCompNo()
                ,assetStdCtctrDetailDTO.getCtctrId()
        };
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * valid assetNo
     * @author ghlee
     * @version $Id:  $
     * @since   1.0
     * 
     * @param assetStdCtctrDetailDTO
     * @return
     */
    public String validCtctrNo(AssetStdCtctrDetailDTO assetStdCtctrDetailDTO, User user)
    {
        
    	QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)                        ");
        query.append("FROM   TACTCTR x                        ");
        query.append("WHERE 1=1 ");
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.ctctr_no", assetStdCtctrDetailDTO.getCtctrNo());
        query.append("AND x.ctctr_id != "+assetStdCtctrDetailDTO.getCtctrId());
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    
    }
}