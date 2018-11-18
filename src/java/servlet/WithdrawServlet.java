/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.AccountJpaController;
import controller.HistoryJpaController;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.RollbackFailureException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import model.Account;
import model.History;

/**
 *
 * @author Windows 10
 */
public class WithdrawServlet extends HttpServlet {
@Resource
UserTransaction utx;
@PersistenceUnit(unitName="TestTestPU")
EntityManagerFactory emf;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String money = request.getParameter("money");
        if(session!=null){
            if(money!=null && money.length()>0){
                AccountJpaController acCtrl = new AccountJpaController(utx, emf);
                Account ac = acCtrl.findAccount(((Account)request.getAttribute("ac")).getAccountid());
                if(ac!=null){
                    int result = ac.getBalance() + Integer.valueOf("money");
                    ac.setBalance(result);
                    
                    try {
                        acCtrl.edit(ac);
                        HistoryJpaController hisCtrl = new HistoryJpaController(utx, emf);
                        int hisid = hisCtrl.getHistoryCount();
                        History his = new History(hisid,ac,"withdraw",Integer.valueOf(money), new Date(), result);
                        hisCtrl.create(his);
                        getServletContext().getRequestDispatcher("/MyAccount").forward(request, response);
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(DepositServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (RollbackFailureException ex) {
                        Logger.getLogger(DepositServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(DepositServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }getServletContext().getRequestDispatcher("/Withdraw.jsp").forward(request, response);
        } else{
            getServletContext().getRequestDispatcher("/Login").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
