package com.emep.zaixian.security.component;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import com.emep.zaixian.model.User;

/**
 * @ClassName: PasswordHelper
 * @Description: 对用户密码进行加密的工具
 * @author com_emep_mpc
 * @date 2016年8月29日 下午2:24:30
 * 
 */
@Component
public class PasswordHelper {

	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	private String algorithmName = "md5";
	private int hashIterations = 2;

	public void setRandomNumberGenerator(
			RandomNumberGenerator randomNumberGenerator) {
		this.randomNumberGenerator = randomNumberGenerator;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}

	/**
	 * @Title: encryptPassword
	 * @Description: 使用shiro的加密工具对密码进行md5加密
	 * @param @param user 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void encryptPassword(User user) {

		user.setSalt(randomNumberGenerator.nextBytes().toHex());

		String newPassword = new SimpleHash(algorithmName, user.getPassword(),
				ByteSource.Util.bytes(user.getUserName() + user.getSalt()),
				hashIterations).toHex();

		user.setPassword(newPassword);
	}

	/**
	 * @Title: againEncryptPassword
	 * @Description: 再次加密
	 * @param @param user 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void againEncryptPassword(User user) {
		String newPassword = new SimpleHash(algorithmName, user.getPassword(),
				ByteSource.Util.bytes(user.getUserName() + user.getSalt()),
				hashIterations).toHex();

		user.setPassword(newPassword);
	}
}
