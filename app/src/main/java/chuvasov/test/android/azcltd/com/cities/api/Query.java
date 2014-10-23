package chuvasov.test.android.azcltd.com.cities.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;


public class Query {
	public int id;
	public int method = -1;
    public int ttl;
	public String url;
	public Map<String, String> params;
    public Map<String, String> headers;
    public Object tag;

    public static final int METHOD_GET = 1;
    public static final int METHOD_POST = 2;

	public String getStringMethod() {
        switch (method) {
            case METHOD_GET:
                return "GET";
            case METHOD_POST:
                return "POST";
            default:
                return "GET";
        }
    }

	
	public static class QueryBuilder {
		private final Query query = new Query();
        private StringBuilder url;
        private StringBuilder urlParams;

        public QueryBuilder id(int id) {
			query.id = id;
			return this;
		}
		
		public QueryBuilder url(String url) {
			this.url = new StringBuilder(url);
			return this;
		}

        public QueryBuilder ttl(int second) {
            query.ttl = second;
            return this;
        }

        public QueryBuilder bodyParam(String key, long value) {
            return bodyParam(key, String.valueOf(value));
        }
		
		public QueryBuilder bodyParam(String key, String value) {
			if (query.params == null) {
				query.params = new HashMap<String, String>();
			}
			
			query.params.put(key, value);

			return this;
		}
		
		public QueryBuilder urlParam(String key, String value) {
			if (urlParams == null) {
				urlParams = new StringBuilder();
			}
			
			try {
                urlParams.append(key).append('=').append(URLEncoder.encode(value, "UTF-8")).append('&');
            } catch (UnsupportedEncodingException ex) {}
			return this;
		}

        public QueryBuilder appendUrlParam(String url) {
            if (urlParams == null) {
                urlParams = new StringBuilder();
            }

            urlParams.append(url).append('&');
            return this;

        }

        public QueryBuilder urlParam(String key, long ... params) {
            String result = "";
            for (Number param : params) {
                result += param + ",";
            }

            if (result.lastIndexOf(',') != -1)
                return urlParam(key, result.substring(0, result.lastIndexOf(',')));
            return this;
        }

        public QueryBuilder urlParam(String key, List<Long> params) {
            String result = "";
            for(Number param: params) {
                result+=param + ",";
            }

            if (result.lastIndexOf(',') != -1)
                return urlParam(key, result.substring(0, result.lastIndexOf(',')));
            return this;
        }

		
		public QueryBuilder path(String path) {
			if (url.charAt(url.length() - 1) != '/' && path.charAt(0) != '/') {
                url.append('/');
            }
            if(url.charAt(url.length() -1) == '/' && path.charAt(0) == '/') {
                url.deleteCharAt(url.length() - 1);
            }
			url.append(path);
			return this;
		}
        public QueryBuilder path(Number path)
        {
            return path(String.valueOf(path));
        }

        public QueryBuilder enums(long ... params) {
            String result = "";
            for(Number param: params) {
                result+=param + ",";
            }

            if (result.lastIndexOf(',') != -1)
                return path(result.substring(0, result.lastIndexOf(',')));
            else
                return this;

        }

        public QueryBuilder enums(List<Long> params) {
            String result = "";
            for(Long param: params) {
                result+=param + ",";
            }

            if (result.lastIndexOf(',') != -1)
                return path(result.substring(0, result.lastIndexOf(',')));
            else
                return this;

        }
		
		public QueryBuilder method(int method) {
			query.method = method;
			return this;
		}

        public QueryBuilder header(String name, String value) {
            if (query.headers == null) {
                query.headers = new TreeMap<String, String>();
            }

            query.headers.put(name, value);
            return this;
        }

        public QueryBuilder tag(Object tag) {
            query.tag = tag;
            return this;
        }

        public Query build() {
			if (urlParams != null) {
                url.append('?').append(urlParams).delete(url.length() - 1, url.length());
            }
			query.url = url.toString();
			return query;
		}


	}

    public static Query queryFromString(String url) {
        return new QueryBuilder().url(url).build();
    }
}
