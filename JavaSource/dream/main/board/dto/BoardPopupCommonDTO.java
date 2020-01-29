package dream.main.board.dto;

import common.bean.BaseDTO;

/**
 * 게시판 공통 DTO
 * @author  pochul2423
 * @version $Id: BoardPopupCommonDTO.java,v 1.2 2014/01/14 01:35:43 pochul2423 Exp $
 * @since   1.0
 * 
 */
public class BoardPopupCommonDTO extends BaseDTO
{
    /** TAB이동시 상세,검색 키 */
    private String boardNo;

    /** 제목 */
    private String description;
    /** 입력자 */
    private String enterBy;
    /** 입력자명 */
    private String enterByName;
    /** 입력일 From */
    private String enterDateFrom;
    /** 입력일 To */
    private String enterDateTo;
    /** 검색 게시번호 */
    private String filterBoardNo;
    

    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getEnterBy()
    {
        return enterBy;
    }
    public void setEnterBy(String enterBy)
    {
        this.enterBy = enterBy;
    }
    public String getEnterByName()
    {
        return enterByName;
    }
    public void setEnterByName(String enterByName)
    {
        this.enterByName = enterByName;
    }
    public String getEnterDateFrom()
    {
        return enterDateFrom;
    }
    public void setEnterDateFrom(String enterDateFrom)
    {
        this.enterDateFrom = enterDateFrom;
    }
    public String getEnterDateTo()
    {
        return enterDateTo;
    }
    public void setEnterDateTo(String enterDateTo)
    {
        this.enterDateTo = enterDateTo;
    }
    public String getBoardNo()
    {
        return boardNo;
    }
    public void setBoardNo(String boardNo)
    {
        this.boardNo = boardNo;
    }
    public String getFilterBoardNo()
    {
        return filterBoardNo;
    }
    public void setFilterBoardNo(String filterBoardNo)
    {
        this.filterBoardNo = filterBoardNo;
    }    
}