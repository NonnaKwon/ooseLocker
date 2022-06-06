package oose.controller;

import oose.persistence.dto.LockerDTO;
import oose.service.LockerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LockerController implements Controller {
    private final LockerService lockerService = new LockerService();

    @Override
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException {
        ModelAndView modelAndView = new ModelAndView();

        System.out.println("\n-------------LockerController---------------\n");
        System.out.println("---------------url-------------\n" + url);
        try {
            if (url.equals("/mngLock/create")) {
                if (request.getMethod().equals("GET")) {
                    modelAndView.setViewName("/lockMng/create_locker");
                } else if (request.getMethod().equals("POST")) {
                    System.out.println("post 들어옴");
                    if (request.getParameter("action").equals("생성")) {
                        try {
                            String facility = request.getParameter("facility");
                            int cost = Integer.parseInt(request.getParameter("cost"));
                            if(cost > 10000 || cost < 0){
                                throw new Exception();
                            }

                            LockerDTO lockerDTO = new LockerDTO(facility,cost);
                            lockerService.add(lockerDTO);

                            modelAndView.setViewName("/lockMng/create_locker");
                            String pageUrl = "/mng/mngLock/create";
                            alert(request, response, pageUrl, "성공적으로 생성 되었습니다");
                        } catch (Exception e) {
                            String pageUrl = "/mng/mngLock/create";
                            alert(request, response, pageUrl, "cost 다시 입력바람");
                        }
                    } else if (request.getParameter("action").equals("돌아가기")) {
                        modelAndView.setViewName("../../index");
                    }
                }
                return modelAndView;
            } else if (url.equals("/mngLock/register")) {
                if (request.getMethod().equals("GET")) {
                    modelAndView.setViewName("/lockMng/register_locker");
                } else if (request.getMethod().equals("POST")) {
                    if (request.getParameter("action").equals("신청")) {
                        try {
                            String facility = request.getParameter("facility");
                            String memId = request.getParameter("member_id");

                            if(lockerService.registerService(facility,memId)){
                                modelAndView.setViewName("/lockMng/register_locker");
                                String pageUrl = "/mng/mngLock/register";
                                alert(request, response, pageUrl, "성공적으로 신청 되었습니다");
                            }else{
                                modelAndView.setViewName("/lockMng/register_locker");
                                String pageUrl = "/mng/mngLock/register";
                                alert(request, response, pageUrl, "비어있는 사물함이 없습니다");
                            }
                        } catch (Exception e) {
                            String pageUrl = "/mng/mngLock/register";
                            alert(request, response, pageUrl, "다시 입력바람");
                        }
                    } else if (request.getParameter("action").equals("돌아가기")) {
                        modelAndView.setViewName("../../index");
                    }
                }
                return modelAndView;

            }
        }catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void alert(HttpServletRequest request, HttpServletResponse response, String url, String msg) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<script>alert('" + msg + "'); location.href='" + url + "';</script>");
        writer.close();
    }

}


