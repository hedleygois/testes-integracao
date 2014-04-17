package br.edu.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Usuario carregaUm(Usuario usuario) {
		return entityManager.find(Usuario.class, usuario.getId());
	}

	public void cadastraUm(Usuario usuario) {
			entityManager.persist(usuario);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listarPor(Usuario usuario) {
		return ((Session)entityManager.getDelegate())
				.createCriteria(Usuario.class)
				.add(Restrictions.ilike("login", usuario.getLogin(), MatchMode.ANYWHERE)).list();
	}

	public void excluiUm(Usuario usuario) {
		usuario = entityManager.merge(usuario);
		entityManager.remove(usuario);
	}

	public void editaUm(Usuario usuario) {
		entityManager.merge(usuario);
	}

}
