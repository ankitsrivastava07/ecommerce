
package ecommerce.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestTokenHeader = request.getHeader("Authorization");
		String token = null;
		String userName = null;
		if (Objects.nonNull(requestTokenHeader) && requestTokenHeader.startsWith("Bearer ")) {
			token = requestTokenHeader.substring(7);

			String api = "http://localhost:8082/users/validateToken?token=" + token;

			RestTemplate restTemplate = new RestTemplate();
			userName = restTemplate.getForObject(api, String.class);

			if (Objects.nonNull(token) && Objects.nonNull(userName)) {
				if (Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())) {
					filterChain.doFilter(request, response);
				} else {
					UserDetails userDetails = null;
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, userName, new ArrayList<>());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					filterChain.doFilter(request, response);
				}
			}
		}
	}
}
