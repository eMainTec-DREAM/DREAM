package dream.consult.comp.warehouse.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.comp.warehouse.dao.MaPtWhDetailDAO;
import dream.consult.comp.warehouse.dto.MaPtWhCommonDTO;
import dream.consult.comp.warehouse.dto.MaPtWhDetailDTO;

/**
 * 부품창고 - 상세 dao
 * 
 * @author ssong
 * @version $Id: MaPtWhDetailDAO.java,v 1.0 2015/12/02 08:25:47 ssong Exp $
 * @since 1.0
 * @spring.bean id="maPtWhDetailDAOTarget"
 * @spring.txbn id="maPtWhDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtWhDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPtWhDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtWhCommonDTO
     * @return
     */
    public MaPtWhDetailDTO findDetail(MaPtWhCommonDTO maPtWhCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT														                            ");
        query.append("        x.wcode												 	                            ");
        query.append("       ,x.wname													                            ");
        query.append("       ,x.is_use isUse											                            ");
        query.append("       ,x.remark    											                            ");
        query.append("       ,x.wh_type whType    									                            ");
        query.append("		 ,dbo.SFACODE_TO_DESC(x.wh_type,'WH_TYPE','SYS','','"+user.getLangId()+"')	whTypeDesc	");
        query.append("       ,x.wh_categ whCateg    									                            ");
        query.append("		 ,dbo.SFACODE_TO_DESC(x.wh_categ,'WH_CATEG','SYS','','"+user.getLangId()+"') whCategDesc	");
        query.append("       ,x.wcode_id wcodeId      								                            ");
        query.append("       ,x.plant plant                                                                 ");
        query.append("       ,(SELECT description                                                                   ");
        query.append("          FROM TAPLANT                                                                        ");
        query.append("         WHERE comp_no = x.comp_no                                                            ");
        query.append("         AND plant = x.plant)             plantDesc                                           ");
        query.append("       ,x.out_wcode outWcode      								                            ");
        query.append("       ,x.out_plant outPlant      								                            ");
        query.append("       ,x.comp_no compNo      									                            ");
        query.append("       ,(SELECT aa.description 	        				                                    ");
        query.append("       	FROM TACOMP aa 	        					                                    ");
        query.append("         WHERE aa.comp_no = x.comp_no) 	compDesc		                                    ");
        query.append("FROM   TAWAREHOUSE x											                            ");
        query.append("WHERE  1 = 1       											");
        query.getAndQuery("x.wcode_id", maPtWhCommonDTO.getWcodeId());

        MaPtWhDetailDTO maPtWhDetailDTO = 
        		(MaPtWhDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPtWhDetailDTO()));
        
        return maPtWhDetailDTO;
    }
    
    public int insertDetail(MaPtWhDetailDTO maPtWhDetailDTO) 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("INSERT INTO TAWAREHOUSE (                                 ");
        query.append("  wcode,   wname,   is_use,    remark,                    ");
        query.append("  wh_type, wcode_id,comp_no,out_wcode,out_plant,wh_categ, ");
        query.append("  plant                                                   ");
        query.append(") VALUES (                                                ");
        query.append("  ?,         ?,          ?,          ?,                   ");
        query.append("  ?,         ?,          ? ,?,?,?,                        ");
        query.append("  ?                                                       ");
        query.append(")                                                         ");
        
        Object[] objects = new Object[] {
        		maPtWhDetailDTO.getWcode(),
        		maPtWhDetailDTO.getWname(),
        		maPtWhDetailDTO.getIsUse(),
        		maPtWhDetailDTO.getRemark(),
        		maPtWhDetailDTO.getWhType(),
        		maPtWhDetailDTO.getWcodeId(),
        		maPtWhDetailDTO.getCompNo(),
        		maPtWhDetailDTO.getOutWcode(),
        		maPtWhDetailDTO.getOutPlant(),
        		maPtWhDetailDTO.getWhCateg(),
        		maPtWhDetailDTO.getPlant()
        };
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}
    
    /**
     * detail stock
     * stock_qty는 업데이트 하지 않는다. 입고확정,취소시 프로시져 통해 변경됨. 
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtWhDetailDTO
     * @return
     */
    public int updateDetail(MaPtWhDetailDTO maPtWhDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAWAREHOUSE SET		   ");
    	query.append("	     wcode        = ?,         ");
    	query.append("	     wname        = ?,         ");
    	query.append("	     is_use       = ?,         ");
    	query.append("	     remark       = ?,         ");
    	query.append("	     plant        = ?,         ");
    	query.append("	     out_wcode       = ?,         ");
    	query.append("	     out_plant       = ?,         ");
    	query.append("	     wh_type      = ?,          ");
    	query.append("	     wh_categ      = ?          ");
    	query.append("WHERE  wcode_id     = ?	       ");
    	
    	Object[] objects = new Object[] {
    			maPtWhDetailDTO.getWcode(),
    			maPtWhDetailDTO.getWname(),
    			maPtWhDetailDTO.getIsUse(),
    			maPtWhDetailDTO.getRemark(),
    			maPtWhDetailDTO.getPlant(),
    			maPtWhDetailDTO.getOutWcode(),
    			maPtWhDetailDTO.getOutPlant(),
    			maPtWhDetailDTO.getWhType(),
    			maPtWhDetailDTO.getWhCateg(),
    			maPtWhDetailDTO.getWcodeId()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
   
}