package Learn;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class RestAuthenticationEntryPoint  { //implements AuthenticationEntryPoint
//	@Override
//	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//		ObjectMapper mapper = new ObjectMapper();
//		Map<String,String> map = Map.of("error","請先登入才能進行操作");
//
//		String error = mapper.writeValueAsString(map);
//		response.setContentType("application/json;charset=UTF-8");
//		response.setCharacterEncoding("UTF-8");
//
//		response.setStatus(response.SC_UNAUTHORIZED);
//		PrintWriter writer = response.getWriter();
//		writer.write(error);
//		writer.flush();
//		writer.close();
//	}

}
